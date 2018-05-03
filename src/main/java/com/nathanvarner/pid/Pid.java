package com.nathanvarner.pid;

public class Pid {
    private double Kp;
    private double Ki;
    private double Kd;

    private double setpoint;

    private double runningI = 0;
    private long lastI, lastD = 0;
    private double lastError = 0;

    public Pid(double Kp, double Ki, double Kd, double setpoint) {
        this.Kp = Kp;
        this.Ki = Ki;
        this.Kd = Kd;

        this.setpoint = setpoint;

        this.lastI = System.currentTimeMillis();
        this.lastD = System.currentTimeMillis();
    }

    public double pid(double currValue) {
        return p(currValue) + i(currValue) + d(currValue);
    }

    public double p(double currValue) {
        double error = this.setpoint - currValue;
        return this.Kp * error;
    }

    /**
     * Calculate the integral part of PID
     * @param currValue The current value that needs to hit the setpoint.
     * @param dt The difference in time between the last call of this function and this one, in ms.
     * @return The integral component.
     */
    public double i(double currValue, long dt) {
        this.lastI = System.currentTimeMillis();
        this.runningI = this.runningI + currValue * dt;
        return this.Ki * this.runningI;
    }

    public double i(double currValue) {
        long dt = System.currentTimeMillis() - this.lastI;
        return i(currValue, dt);
    }

    public void resetI() {
        this.lastI = System.currentTimeMillis();
        this.runningI = 0;
    }

    public double d(double currValue, long dt) {
        this.lastD = System.currentTimeMillis();
        double error = this.setpoint - currValue;
        double derivative = (error - this.lastError) / dt;
        this.lastError = error;
        return this.Kd * derivative;
    }

    public double d(double currValue) {
        long dt = System.currentTimeMillis() - this.lastD;
        return d(currValue, dt);
    }

    public void resetD() {
        this.lastD = System.currentTimeMillis();
        this.lastError = 0;
    }
}
