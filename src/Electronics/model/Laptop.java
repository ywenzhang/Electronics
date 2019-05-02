package Electronics.model;
public class Laptop extends ElectronicProductDetails{
		protected String Producer;
		protected String Display_Size;
		protected String Operating_System;
		protected String CPU_Model_Family;
		protected String Memory_Size;
		protected String Hard_Disk_Size;
		protected int Memory_Type;
		protected int CPU_Type;
		protected int Hard_Disk_Type;
		
		public Laptop (String web_scraper_order, String ProductName, String Name_hef, 
				double Price, double Rating, int Number_of_Reviews,String Producer, String Display_Size, String Operating_System,
				String CPU_Model_Family, String Memory_Size, String Hard_Disk_Size,int Memory_Type, int CPU_Type, int Hard_Disk_Type) {
			super(web_scraper_order, ProductName, Name_hef, Price, Rating,Number_of_Reviews);
			//System.out.printf(web_scraper_order);
			this.Producer = Producer;
			this.Display_Size= Display_Size;
			this.Operating_System = Operating_System;
			this.CPU_Model_Family = CPU_Model_Family;
			this.Memory_Size = Memory_Size;
			this.Hard_Disk_Size = Hard_Disk_Size;
			this.Memory_Type = Memory_Type;
			this.CPU_Type = CPU_Type;
			this.Hard_Disk_Type = Hard_Disk_Type;
			
		}
		
		public Laptop(String web_scraper_order) {
			super(web_scraper_order);
		}
		
		public String getProducer() {
			return Producer;
		}

		public void setProducer(String producer) {
			Producer = producer;
		}

		public String getDisplay_Size() {
			return Display_Size;
		}

		public void setDisplay_Size(String display_Size) {
			Display_Size = display_Size;
		}

		public String getOperating_System() {
			return Operating_System;
		}

		public void setOperating_System(String operating_System) {
			Operating_System = operating_System;
		}

		public String getCPU_Model_Family() {
			return CPU_Model_Family;
		}

		public void setCPU_Model_Family(String cPU_Model_Family) {
			CPU_Model_Family = cPU_Model_Family;
		}

		public String getMemory_Size() {
			return Memory_Size;
		}

		public void setMemory_Size(String memory_Size) {
			Memory_Size = memory_Size;
		}

		public String getHard_Disk_Size() {
			return Hard_Disk_Size;
		}

		public void setHard_Disk_Size(String hard_Disk_Size) {
			Hard_Disk_Size = hard_Disk_Size;
		}

		public int getMemory_Type() {
			return Memory_Type;
		}

		public void setMemory_Type(int memory_Type) {
			Memory_Type = memory_Type;
		}

		public int getCPU_Type() {
			return CPU_Type;
		}

		public void setCPU_Type(int cPU_Type) {
			CPU_Type = cPU_Type;
		}

		public int getHard_Disk_Type() {
			return Hard_Disk_Type;
		}

		public void setHard_Disk_Type(int hard_Disk_Type) {
			Hard_Disk_Type = hard_Disk_Type;
		}	
}
