package com.nhnacademy.clientbatch.service;

public interface ClientBatchService {
    /**
     * 3달간 로그인 하지않은 유저를 매일자정 휴면처리 시키는 함수입니다.
     */
    void updateInactiveClients();
}
