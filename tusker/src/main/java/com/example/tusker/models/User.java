package com.example.tusker.models;

public record User(
    Long id,
    String email,
    String password,
    String name
) {
    public static class Builder {
        Long id;
        String email;
        String password;
        String name;
        public Builder Id(Long id){
            this.id = id;
            return this;
        }
        public Builder Email(String e){
            this.email = e;
            return this;
        }
        public Builder Password(String hashedPass){
            this.password = hashedPass;
            return this;
        }
        public Builder Name(String username){
            this.name = username;
            return this;
        }
        public User build(){
            return new User(id, email, password, name);
        }
    }
}
