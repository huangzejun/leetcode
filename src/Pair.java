
public class Pair {
	String  equipmentid;
	String areaid;
	public Pair(String equipmentid, String areaid) {
		// TODO Auto-generated constructor stub
		this.equipmentid = equipmentid;
		this.areaid = areaid;
	}
	
	public String getEquipmentid() {
		return equipmentid;
	}

	public void setEquipmentid(String equipmentid) {
		this.equipmentid = equipmentid;
	}
	
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int result = 17;  
        result = result * 31 + equipmentid.hashCode();  
        result = result * 31 + areaid.hashCode();  
          
        return result;  
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj == this)
			return true;
		if(obj instanceof Pair){
			Pair pair = (Pair) obj;
			return pair.equipmentid.equals(equipmentid)
					&&pair.areaid.equals(areaid);
		}
		return super.equals(obj);
	}
}
