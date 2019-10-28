package com.ruoyi.system.domain;

public class YdUserLogin {

    private	long id            ;
    private	long userId       ;
    private	int identityType ;
    private	String identityTest ;
    private	String identifier    ;
    private	String credential    ;
    private	String tokenValue   ;
    private	String abstractValue;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getIdentityType() {
        return identityType;
    }

    public void setIdentityType(int identityType) {
        this.identityType = identityType;
    }

    public String getIdentityTest() {
        return identityTest;
    }

    public void setIdentityTest(String identityTest) {
        this.identityTest = identityTest;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public String getAbstractValue() {
        return abstractValue;
    }

    public void setAbstractValue(String abstractValue) {
        this.abstractValue = abstractValue;
    }

    @Override
    public String toString() {
        return "YdUserLogin{" +
                "id=" + id +
                ", userId=" + userId +
                ", identityType=" + identityType +
                ", identityTest='" + identityTest + '\'' +
                ", identifier='" + identifier + '\'' +
                ", credential='" + credential + '\'' +
                ", tokenValue='" + tokenValue + '\'' +
                ", abstractValue='" + abstractValue + '\'' +
                '}';
    }
}
