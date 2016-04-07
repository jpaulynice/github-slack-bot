package com.project.config;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(classes = SpringConfig.class)
public abstract class BaseSpringTest extends AbstractTestNGSpringContextTests {
    // nothing to see here
}