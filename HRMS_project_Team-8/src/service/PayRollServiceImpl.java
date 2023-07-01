package service;

import service_interfaces.PayRollService;

public class PayRollServiceImpl implements PayRollService {

	private double total, basicpay, netpay, hra, ta, da, additions;
	private double deductions_sal, pf, esi, gratuity;
	private double ptax = 200;
	private double tax = 0;

	@Override
	public double calculateBasicPay(double basicpay) {
		// TODO Auto-generated method stub
		this.basicpay = basicpay;
		return basicpay;
	}

	@Override
	public double calculateHRA(double fixedpay) {
		this.hra = fixedpay * 0.4;
		return hra;
	}

	@Override
	public double calculateDA(double fixedpay) {
		this.da = fixedpay * 0.3;
		return da;
	}

	@Override
	public double calculateTA(double fixedpay) {
		this.ta = fixedpay * 0.3;
		return ta;
	}

	@Override
	public double calculateAdditions(double additionalPay) {
		this.additions = additionalPay;
		return additions;
	}

	@Override
	public double calculatePF(double ctc) {

		this.pf = (0.6 * ctc) / 12;

		return pf;
	}

	@Override
	public double calculateESI(double ctc) {

		this.esi = (0.15 * ctc) / 12;

		return esi;
	}

	@Override
	public double calculateGratuity(double ctc) {

		this.gratuity = (0.25 * ctc) / 12;

		return gratuity;
	}

	@Override
	public double calculateDeductions() {

		deductions_sal = gratuity + esi + pf + ptax + tax;
		return deductions_sal;
	}

	@Override
	public double calculateTax(double ctc) {

		if (ctc <= 500000) {
			this.tax = 0;
		} else if (ctc > 500000 && ctc <= 800000) {
			this.tax = ((0.05 * (ctc - 500000)) / 12);
		} else if (ctc > 800000 && ctc <= 1100000) {
			this.tax = ((0.1 * (ctc - 800000)) / 12);
		} else {
			this.tax = ((0.2 * (ctc - 1100000)) / 12);
		}

		return this.tax;
	}

	@Override
	public double calculateNetPay() {

		this.netpay = total - deductions_sal;

		return netpay;
	}

	@Override
	public double calculateTotalPay() {
		this.total = basicpay + hra + ta + da + additions;
		return total;
	}

}
