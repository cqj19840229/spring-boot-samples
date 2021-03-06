package com.peterwanghao.samples.spring.boot.jta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;

/**
 * @ClassName: TellerService
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: wanghao
 * @date: 2019年8月22日 上午10:56:29
 * @version V1.0
 * 
 */
@Service
public class TellerService {
	private final BankAccountService bankAccountService;
	private final AuditService auditService;
	private final UserTransaction userTransaction;

	@Autowired
	public TellerService(BankAccountService bankAccountService,
			AuditService auditService, UserTransaction userTransaction) {
		this.bankAccountService = bankAccountService;
		this.auditService = auditService;
		this.userTransaction = userTransaction;
	}

	@Transactional
	public void executeTransfer(String fromAccontId, String toAccountId,
			BigDecimal amount) {
		bankAccountService.transfer(fromAccontId, toAccountId, amount);
		auditService.log(fromAccontId, toAccountId, amount);
		BigDecimal balance = bankAccountService.balanceOf(fromAccontId);
		if (balance.compareTo(BigDecimal.ZERO) < 0) {
			throw new RuntimeException("余额不足!");
		}
	}

	public void executeTransferProgrammaticTx(String fromAccontId,
			String toAccountId, BigDecimal amount) throws Exception {
		userTransaction.begin();
		bankAccountService.transfer(fromAccontId, toAccountId, amount);
		auditService.log(fromAccontId, toAccountId, amount);
		BigDecimal balance = bankAccountService.balanceOf(fromAccontId);
		if (balance.compareTo(BigDecimal.ZERO) < 0) {
			userTransaction.rollback();
			throw new RuntimeException("余额不足!");
		} else {
			userTransaction.commit();
		}
	}
}
