package com.zzy.study.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author zhangzhiyuan
 * @since 2019-04-05
 */
@TableName("sys_privilege")
public class SysPrivilege extends Model<SysPrivilege> {

    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 权限名称
     */
    @TableField("privilege_name")
    private String privilegeName;
    /**
     * 权限URL
     */
    @TableField("privilege_url")
    private String privilegeUrl;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public String getPrivilegeUrl() {
        return privilegeUrl;
    }

    public void setPrivilegeUrl(String privilegeUrl) {
        this.privilegeUrl = privilegeUrl;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysPrivilege{" +
        "id=" + id +
        ", privilegeName=" + privilegeName +
        ", privilegeUrl=" + privilegeUrl +
        "}";
    }
}
