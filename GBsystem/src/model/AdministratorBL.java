package model;

public class AdministratorBL {
	public Administrator login(String id) {
		Administrator admin = null;

		AdministratorDAO dao = new AdministratorDAO();
		admin = dao.findAdministratorByKey(id);

		return admin;
	}
}
