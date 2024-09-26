package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/*
 
What It Demonstrates:

Efficient form field handling by dynamically locating fields based on their labels.
Flexible solution that works for any field without hardcoding locators.
Maintains ease of interaction with both SetField for setting values and ClearForm for clearing all fields in a form.
Handles different types of input fields (text, checkbox, etc.) in a versatile way.

Good Practices:

Dynamic Element Handling: Fields are located dynamically, ensuring adaptability to changes in form structure.
Code Reusability: Single methods handle a variety of cases, reducing redundancy and improving maintainability.
Encapsulation: Field interaction logic is kept within specific methods, promoting modular code.
Separation of Concerns: Each method has a clear responsibility—setting fields, clearing the form—keeping the logic clean and focused.
Scalability: The approach can easily handle new fields or types with minimal changes to the codebase.

 */

public class OCForm {

	WebElement form; 
	
	public OCForm(WebElement form) {
		this.form = form;
		
	}
		
	public void SetField(String name, String value) {
		try {
			//If your form uses labels in different languages or the case of the label text can vary, you might need to handle this (e.g., case-insensitivity, or using XPath contains() to match partial text).
			//WebElement label = form.findElement(By.xpath(String.format("//label[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '%s')]", name.toLowerCase())));

		    WebElement label = form.findElement(By.xpath(String.format("//label[text()='%s']", name)));
		    String labelforfieldid = label.getAttribute("for");
		    WebElement field = form.findElement(By.id(labelforfieldid));
		    
		    //fields like dropdowns, radio buttons, or checkboxes. Extend to handle different types of input based on the tagName or type of the field.
		    String tagName = field.getTagName();
		    if ("input".equals(tagName)) {
		        field.clear();
		        field.sendKeys(value);
		    } else if ("select".equals(tagName)) {
		        Select dropdown = new Select(field);
		        dropdown.selectByVisibleText(value);
		    } else if ("textarea".equals(tagName)) {
		        field.clear();
		        field.sendKeys(value);
		    }
		    
		} catch (NoSuchElementException e) {
		    System.out.println("Field or label not found: " + name);
		}
	}
	
	public void Action(String action) {
	    try {
	        // Find a button or submit input inside the form and click it.
	        WebElement button = form.findElement(By.xpath(String.format("//button[text()='%s'] | //input[@type='submit' and @value='%s']", action, action)));
	        button.click();
	    } catch (NoSuchElementException e) {
	        System.out.println("Button or submit input with text '" + action + "' not found.");
	    }
	}

	
	public void Agree(boolean agree) {
		//Get the control
		WebElement agreement = form.findElement(By.name("agree"));
		if (agreement.isSelected() != agree ) agreement.click();
		
	}
	
	public void ClearForm() {
	    // Find all input, textarea, and select fields inside the form
	    List<WebElement> fields = form.findElements(By.xpath("//input | //textarea | //select"));

	    for (WebElement field : fields) {
	        // Check if the field is enabled and not read-only
	        if (field.isEnabled() && !"readonly".equals(field.getAttribute("readonly"))) {

	            // Handle different input types
	            String fieldType = field.getAttribute("type");

	            if (fieldType == null || fieldType.equals("text") || fieldType.equals("password") || fieldType.equals("email")) {
	                // Clear text, password, email fields
	                field.clear();
	            } else if (fieldType.equals("checkbox") || fieldType.equals("radio")) {
	                // Uncheck checkboxes and radio buttons
	                if (field.isSelected()) {
	                    field.click();
	                }
	            } else if (fieldType.equals("select-one")) {
	                // Reset select elements (drop-downs)
	                Select select = new Select(field);
	                select.selectByIndex(0); // Reset to the first option
	            }
	        }
	    }
	}

}
