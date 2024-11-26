package modelo;

import java.io.*;
import java.util.UUID;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String username;
    private String password;
    private int score;
    private int level;
    private int configId;

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println(username);
    }
    
    public Usuario() {
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", password=" + password + ", score=" + score
				+ ", level=" + level +"]";
	}
    
    public String crearRegistroTexto() {
    	String username = this.username.replace("','", "\",\"");
    	String password = this.password.replace("','", "\",\"");
    	String recordString = id + "','" +username+"','"+ password+"','"+
    							score+"','"+level+"','"+configId;
    	recordString = recordString.replace("\n", "\\n");
    	return recordString;
    }
    
    public static Usuario deserializeUsuario(String userInfo) {
    	String[] data = userInfo.replace("\\n", "\n").split("','");
    	Usuario user = new Usuario();
    	try {
    		user.setId(Integer.parseInt(data[0]));
    		user.setUsername(data[1]);
    		user.setPassword(data[2]);
    		user.setScore(Integer.parseInt(data[3]));
    		user.setLevel(Integer.parseInt(data[4]));
    		user.setConfigId(Integer.parseInt(data[5]));
		} catch (Exception e) {
			//invalid data format
			return null;
		}
    	return user;
    }
    

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
    	if (level >= 0 && level < 11) {
    		this.level = level;
		}
    }

	public int getConfigId() {
		return configId;
	}

	public void setConfigId(int configId) {
		this.configId = configId;
	}

}
