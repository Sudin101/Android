package com.sudin.jobseeker;

import org.junit.Test;
public class testRegister{
@Test
public void testRegister(){
        RegisterLogic registerLogic = new RegisterLogic("sudin ","sudin","sudin@gmail.com", "sudin kafle");
        boolean result = registerLogic.registerUser();
        assertEquals(true, result);
        }
