package org.sanpao.flare.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

@MappedSuperclass
public class BaseEntity {

	public final static String FIELD_STATUS = "status";
	public final static String FIELD_CREATE_AT = "create_at";
	public final static String FIELD_CREATE_BY = "create_by";
	public final static String FIELD_UPDATE_AT = "update_at";
	public final static String FIELD_UPDATE_BY = "update_by";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@QuerySqlField(index = true)
	protected Long id;

	@QuerySqlField
	@Column(name = FIELD_STATUS)
	protected String status;

	@QuerySqlField
	@Column(name = FIELD_CREATE_AT)
	protected Date createAt;

	@QuerySqlField
	@Column(name = FIELD_CREATE_BY)
	protected Long createBy;

	@QuerySqlField
	@Column(name = FIELD_UPDATE_AT)
	protected Date updateAt;

	@QuerySqlField
	@Column(name = FIELD_UPDATE_BY)
	protected Long updateBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

}
