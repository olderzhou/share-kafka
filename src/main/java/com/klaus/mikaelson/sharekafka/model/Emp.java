package com.klaus.mikaelson.sharekafka.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.ToString;

@Entity
@Table(name = "t_emp", uniqueConstraints=@UniqueConstraint(columnNames={"ename", "sar"},name="unique_ename_sar"),
	indexes = { 
		@Index(name = "idx_ename", columnList = "ename", unique = false) ,
		@Index(name = "idx_ename_did", columnList = "ename,did", unique = false) 
		})
@ToString
public class Emp implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long eid;
	private String ename;
	private String sex;
	private Date hire;
	private float sar;
	private int did;

	private Timestamp updateTime;
	private Timestamp addTime;
	private long version;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "eid", columnDefinition = "BIGINT NOT NULL COMMENT 'emp id'")
	public Long getEid() {
		return eid;
	}

	public void setEid(Long eid) {
		this.eid = eid;
	}

	@Column(name = "ename", columnDefinition = "varchar(50)  COMMENT 'name of emp'")
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	@Column(name = "sex", columnDefinition = "varchar(10)  COMMENT 'sex of emp'")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "hire", columnDefinition = "DATETIME COMMENT 'hire date of emp'")
	public Date getHire() {
		return hire;
	}

	public void setHire(Date hire) {
		this.hire = hire;
	}

	public float getSar() {
		return sar;
	}

	public void setSar(float sar) {
		this.sar = sar;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	@UpdateTimestamp
	@Column(name = "update_time", columnDefinition = "DATETIME COMMENT '最后更新时间'")
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@CreationTimestamp
	@Column(name = "add_time", columnDefinition = "DATETIME COMMENT '添加时间'")
	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	@Version
	@Column(name = "version", columnDefinition = "bigint COMMENT '版本号'")
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

}
