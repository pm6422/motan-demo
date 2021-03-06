package org.infinity.motan.democommon.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Spring Data MongoDB collection for the Authority entity.
 */
@ApiModel("权限")
@Document(collection = "Authority")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String DEVELOPER = "ROLE_DEVELOPER";

    public static final String ROLE_SAMPLE_SOUND_USER = "ROLE_SAMPLE_SOUND_USER";

    public static final String SYSTEM_ACCOUNT = "system";

    @ApiModelProperty(value = "权限名称", required = true)
    @NotNull
    @Size(min = 3, max = 16)
    @Pattern(regexp = "^[A-Z_]+$", message = "{EP5902}")
    @Id
    private String name;

    @ApiModelProperty(value = "是否为系统权限")
    private Boolean systemLevel;

    @ApiModelProperty(value = "是否可用")
    private Boolean enabled;

    public Authority(Boolean enabled) {
        this.enabled = enabled;
    }

}
