package com.project.service;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import com.project.config.SpringConfig;

@ContextConfiguration(classes = SpringConfig.class)
public class BaseSpringTest extends AbstractTestNGSpringContextTests {
    // nothing to see here
}