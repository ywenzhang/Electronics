package Electronics.model;

public class PurchaseHistory 
{
	protected int purchaseId;
	protected Users user;
	protected ElectronicProductDetails electronicProductDetails;
	public PurchaseHistory(int purchaseId, Users user, ElectronicProductDetails electronicProductDetails)
	{
		this.purchaseId = purchaseId;
		this.user = user;
		this.electronicProductDetails = electronicProductDetails;
	}
	
	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public ElectronicProductDetails getElectronicProductDetails() {
		return electronicProductDetails;
	}
	public void setElectronicProductDetails(ElectronicProductDetails electronicProductDetails) {
		this.electronicProductDetails = electronicProductDetails;
	}
	
}
