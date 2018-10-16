package com.staliang.qiwi.auth;

import com.staliang.qiwi.model.Extra;
import com.staliang.qiwi.model.RequestWithExtraPassword;

public interface AuthEngine {

    boolean isAuthByRequest();

    void addAuthInfoToRequest(RequestWithExtraPassword request);

    static AuthEngine authByPassword(String password) {
        return new AuthByPassword(password);
    }

    class AuthByPassword implements AuthEngine {

        private final String password;

        public AuthByPassword(String password) {
            this.password = password;
        }

        @Override
        public boolean isAuthByRequest() {
            return true;
        }

        @Override
        public void addAuthInfoToRequest(RequestWithExtraPassword request) {
           request.setExtra(new Extra.ExtraPassword(password));
        }
    }
}
