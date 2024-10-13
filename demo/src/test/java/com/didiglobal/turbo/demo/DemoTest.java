package com.didiglobal.turbo.demo;

import com.didiglobal.turbo.demo.service.AfterSaleServiceImpl;
import com.didiglobal.turbo.demo.service.LeaveServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author rick
 * @Date 2022/4/11 12:53
 */
@SpringBootTest(classes = DemoApplication.class)
public class DemoTest {
    @Autowired
    private AfterSaleServiceImpl afterSaleService;
    @Autowired
    private LeaveServiceImpl leaveService;

    @Test
    public void runAfterSaleDemo(){
        afterSaleService.run();
    }

    @Test
    public void runLeaveDemo(){
        leaveService.run();
    }
}
