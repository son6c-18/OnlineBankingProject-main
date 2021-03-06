package com.se2_project.group12.demoEBanking.Model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
@PrimaryKeyJoinColumn(name = "billId")
public class Bill extends Transaction {

	@Column(updatable = false)
	@CreationTimestamp
	private LocalDateTime time_created;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "serviceId", foreignKey = @ForeignKey(name = "fk_service_bill"))
	@JsonIgnoreProperties(value = "bills")
	private Service service;

	@Column
	private long pricetag;
	
	public LocalDateTime getTime_created() {
		return time_created;
	}

	public void setTime_created(LocalDateTime time_created) {
		this.time_created = time_created;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public long getPricetag() {
		return pricetag;
	}

	public void setPricetag(long pricetag) {
		this.pricetag = pricetag;
	}
	
	

}
