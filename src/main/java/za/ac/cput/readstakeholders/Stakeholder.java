
package za.ac.cput.readstakeholders;

import java.io.Serializable;

/**
 * Stakeholder.java;
 * This is a stakeholder class 
 * @author Anele Aneal Tose - 216079292
 * Date: 02-June-2021
 */
public class Stakeholder implements Serializable{
    private String stHolderId;

    public Stakeholder() {
    }
    
    public Stakeholder(String stHolderId) {
        this.stHolderId = stHolderId;
    }
    
    public String getStHolderId() {
        return stHolderId;
    }

    public void setStHolderId(String stHolderId) {
        this.stHolderId = stHolderId;
    }

    @Override
    public String toString() {
       return stHolderId;
    }

}