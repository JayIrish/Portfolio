package com.example.model.modelDirectrice;

public class Directrice {
    int idUtil;
    String pwUtil;
    String userName;
    String telephone;
    
    public Directrice() {

    }

    public Directrice(String userName, String pwUtil, String telephone) {
        this.setUserName(userName);
        this.setPwUtil(pwUtil);
        this.setTelephone(telephone);
    }

    public int getIdUtil() {
        return idUtil;
    }

    public void setIdUtil(int idUtil) {
        this.idUtil = idUtil;
    }

    public String getPwUtil() {
        return pwUtil;
    }

    public void setPwUtil(String pwUtil) {
        this.pwUtil = pwUtil;
    }    
    
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
    public String toString() {
        return "Directrice: " + idUtil + ", userName : " + userName + ", pwUtil: " + pwUtil + ", telephone : " + telephone;
    }
}
