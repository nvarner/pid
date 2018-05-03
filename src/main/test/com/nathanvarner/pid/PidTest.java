package com.nathanvarner.pid;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("PID Test")
class PidTest {
    @Test
    void pid() {
    }

    @Test
    void p() {
        final double setpoint = 100;
        double currValue = 0;

        Pid pid = new Pid(0.952, 0, 0, setpoint);

        double dCV = pid.p(currValue);
        assertEquals(95.2, dCV, 0.00001);
    }

    @Test
    void i() {
    }

    @Test
    void i1() {
    }

    @Test
    void resetI() {
    }

    @Test
    void d() {
    }

    @Test
    void d1() {
    }

    @Test
    void resetD() {
    }
}