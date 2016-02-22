package server;

import staff.*;

import java.math.BigInteger;
import java.util.HashMap;

public class AccessBase {
	public static final String SU = "Skånes Universitetssjukhus";
	public static final String KI = "Karolinska Institutet";
	public static final String SB = "Sahlgrenska SexualSjukhus";
	private HashMap<BigInteger, User> userDatabase;

	public AccessBase() {
		userDatabase = new HashMap<BigInteger, User>();

	}

	public User getUser(BigInteger Biggie) {
		return userDatabase.get(Biggie);
	}

	public void createUser(BigInteger Biggie, User staffMember) throws Exception {

		if (userDatabase.get(Biggie) == null) {
			userDatabase.put(Biggie, staffMember);
		} else {
			throw new Exception();
		}
	}

}
