package org.infinity.motan.democommon.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.infinity.motan.democommon.domain.base.AbstractAuditableDomain;
import org.infinity.motan.democommon.domain.base.BaseAdminMenu;
import org.infinity.motan.democommon.dto.AdminMenuTreeDTO;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * Spring Data MongoDB collection for the AdminMenu entity.
 */
@ApiModel("管理系统菜单")
@Document(collection = "AdminMenu")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class AdminMenu extends BaseAdminMenu implements Serializable {

    private static final long serialVersionUID = 5423774898556939254L;

    public AdminMenu(String code, String name, Integer level, String url,
                     Integer sequence, String parentId) {
        super(code, name, level, url, sequence, parentId);
    }
}