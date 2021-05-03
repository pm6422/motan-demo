package org.infinity.motan.democlient.controller;

import com.weibo.api.motan.config.springsupport.annotation.MotanReferer;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.infinity.motan.democlient.component.HttpHeaderCreator;
import org.infinity.motan.democlient.exception.NoDataFoundException;
import org.infinity.motan.democommon.domain.App;
import org.infinity.motan.democommon.service.AppService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static javax.servlet.http.HttpServletResponse.*;
import static org.infinity.motan.democlient.utils.HttpHeaderUtils.generatePageHeaders;

/**
 * REST controller for managing apps.
 */
@RestController
@Api(tags = "应用管理")
@Slf4j
public class AppController {

    @MotanReferer(requestTimeout = 10000)
    private AppService appService;

    private final HttpHeaderCreator httpHeaderCreator;

    public AppController(HttpHeaderCreator httpHeaderCreator) {
        this.httpHeaderCreator = httpHeaderCreator;
    }

    @ApiOperation("创建应用")
    @ApiResponses(value = {@ApiResponse(code = SC_CREATED, message = "成功创建")})
    @PostMapping("/api/apps")
    public ResponseEntity<Void> create(@ApiParam(value = "应用", required = true) @Valid @RequestBody App domain) {
        log.debug("REST request to create app: {}", domain);
        appService.insert(domain);
        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(httpHeaderCreator.createSuccessHeader("SM1001", domain.getName())).build();
    }


    @ApiOperation("分页检索应用列表")
    @ApiResponses(value = {@ApiResponse(code = SC_OK, message = "成功检索")})
    @GetMapping("/api/apps")
    public ResponseEntity<List<App>> find(Pageable pageable) {
        Page<App> apps = appService.findAll(pageable);
        return ResponseEntity.ok().headers(generatePageHeaders(apps)).body(apps.getContent());
    }

    @ApiOperation("根据名称检索应用")
    @ApiResponses(value = {@ApiResponse(code = SC_OK, message = "成功检索"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "应用不存在")})
    @GetMapping("/api/apps/{name}")
    public ResponseEntity<App> findById(@ApiParam(value = "应用名称", required = true) @PathVariable String name) {
        App app = appService.findById(name).orElseThrow(() -> new NoDataFoundException(name));
        return ResponseEntity.ok(app);
    }

    @ApiOperation("更新应用")
    @ApiResponses(value = {@ApiResponse(code = SC_OK, message = "成功更新"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "应用不存在")})
    @PutMapping("/api/apps")
    public ResponseEntity<Void> update(@ApiParam(value = "新的应用", required = true) @Valid @RequestBody App domain) {
        log.debug("REST request to update app: {}", domain);
        appService.update(domain);
        return ResponseEntity.ok()
                .headers(httpHeaderCreator.createSuccessHeader("SM1002", domain.getName())).build();
    }

    @ApiOperation(value = "根据名称删除应用", notes = "数据有可能被其他数据所引用，删除之后可能出现一些问题")
    @ApiResponses(value = {@ApiResponse(code = SC_OK, message = "成功删除"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "应用不存在")})
    @DeleteMapping("/api/apps/{name}")
    public ResponseEntity<Void> delete(@ApiParam(value = "应用名称", required = true) @PathVariable String name) {
        log.debug("REST request to delete app: {}", name);
        appService.findById(name).orElseThrow(() -> new NoDataFoundException(name));
        appService.deleteById(name);
        return ResponseEntity.ok()
                .headers(httpHeaderCreator.createSuccessHeader("SM1003", name)).build();
    }
}
