package com.company.main;



import com.company.enums.InsuranceCompanies;
import com.company.enums.PetrolStation;
import com.company.expenses.CarExpenses;

public class Main {

	public static void main(String[] args) {
		
        CarExpenses car1 = new CarExpenses("Omniasig", "B17OSG", InsuranceCompanies.CarProtect, 30000, 8, PetrolStation.OMV, 7);
        
        String filePath = "//Users//macbook//Documents//Car1.expenses";
        
        car1.serialize(filePath);
        
        CarExpenses car2 = new CarExpenses("Omniasig", "B19OSG", InsuranceCompanies.DriveSafe, 80000, 6, PetrolStation.EcoFuel, 9);
        
        String filePath2 = "//Users//macbook//Documents//Car2.expenses";
        
        car2.serialize(filePath2);

        
	}

}
