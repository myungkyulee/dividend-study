package spring.study.dividend.model;

import lombok.Data;
import spring.study.dividend.entity.MemberEntity;

import java.util.List;

public class Auth {
    @Data
    public static class SignIn {
        private String username;
        private String password;
    }

    @Data
    public static class SignUp {
        private String username;
        private String password;
        private List<String> roles;

        public MemberEntity toEntity() {
            return MemberEntity.builder()
                    .username(this.username)
                    .password(password)
                    .roles(roles).build();
        }
    }
}
