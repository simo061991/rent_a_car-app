public void dohvat(String upit){
		try{
			//String upit = "select * from automobil";
			rs = stmt.executeQuery(upit);
			while(rs.next()){
				String tip = rs.getString("tip");
				System.out.println(tip);
			}
