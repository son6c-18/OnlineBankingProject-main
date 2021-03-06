package com.se2_project.group12.demoEBanking.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.se2_project.group12.demoEBanking.Helper.CheckValidate;
import com.se2_project.group12.demoEBanking.Helper.ErrorType;
import com.se2_project.group12.demoEBanking.IService.ISaveService;
import com.se2_project.group12.demoEBanking.Model.Account;
import com.se2_project.group12.demoEBanking.Model.InvestType;
import com.se2_project.group12.demoEBanking.Model.Loan;
import com.se2_project.group12.demoEBanking.Model.Save;
import com.se2_project.group12.demoEBanking.Repository.AccountRepository;
import com.se2_project.group12.demoEBanking.Repository.SaveRepository;

@Service
public class SaveService implements ISaveService{
	
	
	@Autowired
	ErrorType errorType;
	
	@Autowired
	CheckValidate checkValidate;
	
	@Autowired
	SaveRepository saveRepository;
	
	@Autowired
	InvestTypeService investService;
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	public Save getSave(int accountId) {
		
		if(investService.isEmpty(accountId)) {
			return null;
		}else {
			if(isEmpty(accountId)) {
				return null;
			}
			Save save = saveRepository.findById(investService.getInvest(accountId).getInvestTypeId()).get();
			return save;
		}
	}

	@Override
	public String addSave(String save) {
		Gson gson = new GsonBuilder().setDateFormat("dd/MMM/yyyy HH:mm:ss").create();
		Save sa = gson.fromJson(save, Save.class);
		
		if(!accountRepository.existsById(sa.getAccount().getAccountId())) {
			return errorType.isNotExisted("Account ");
		}	
		
		if(investService.isEmpty(sa.getAccount().getAccountId()))
		{
			if(checkValidate.getOk(sa)) {
				saveRepository.save(sa);
				return errorType.getSuccesful();
			}
			return errorType.isValidated(sa.getInvestTypeId());
		}
		
		
		return errorType.isExisted(sa.getClass().getSimpleName());
	}

	@Override
	public String deleteSave(int accountId) {
		if( isEmpty(accountId)) {
			return errorType.isNotExisted("Save");
		}
		Save save = getSave(accountId);
		if(save.getAmount() >0) {
			Account account = save.getAccount();
			account.setBalance(account.getBalance()+ save.getAmount());
		}
		investService.deleteInvestType(accountId);
		return errorType.getSuccesful();
	}

	@Override
	public String editSave(String save) {
		Gson gson = new GsonBuilder().setDateFormat("dd/MMM/yyyy HH:mm:ss").create();
		Save sa = gson.fromJson(save, Save.class);
		if (isEmpty(sa.getAccount().getAccountId())) {
			return errorType.isNotExisted("save");
		} else {

			if (checkValidate.getOk(sa)) {
				Save saveCurrent = getSave(sa.getAccount().getAccountId());
				if (sa.getAmount() != saveCurrent.getAmount())
					saveCurrent.setAmount(sa.getAmount());
				if (sa.getDescription() != null)
					saveCurrent.setDescription(sa.getDescription());
				if (sa.getDuration() != saveCurrent.getDuration())
					saveCurrent.setDuration(sa.getDuration());
				if (sa.getRate() != saveCurrent.getRate())
					saveCurrent.setRate(sa.getRate());
				saveCurrent.setTimeCreated(LocalDateTime.now());
				saveRepository.save(saveCurrent);
				return errorType.getSuccesful();
			}
			return errorType.isValidated("save");
		}
	}

	@Override
	public boolean isEmpty(int accountId) {
		if(saveRepository.existsById(investService.getInvest(accountId).getInvestTypeId())) {
			return false;
		}
		return true;
		
	}
	
	

}
