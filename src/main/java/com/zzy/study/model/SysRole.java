package com.zzy.study.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author zhangzhiyuan
 * @since 2019-04-05
 */
@TableName("sys_role")
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 角色名
     */
    @TableField("role_name")
    private String roleName;
    /**
     * 有效标志
     */
    private Integer enabled;
    /**
     * 创建人
     */
    @TableField("create_by")
    private Long createBy;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    private SysUser user;

    public SysUser getUser() {
        return user;
    }

    public void setSysUser(SysUser user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysRole{" +
        "id=" + id +
        ", roleName=" + roleName +
        ", enabled=" + enabled +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        "}";
    }
}
