package com.example.hwstream.service;

import com.example.hwstream.IncorrectLastNameException;
import com.example.hwstream.IncorrectNameException;
import org.apache.commons.lang3.StringUtils;

public class ValidatorService {

   public String validateFirstName(String firstName) {
       if(!StringUtils.isAlpha(firstName)){
           throw new IncorrectNameException();
       }
       return StringUtils.capitalize(firstName.toLowerCase());
   }

    public String validateLastName(String lastName) {
       String [] lastNames = lastName.split("-");
        for (int i = 0; i< lastNames.length; i++) {
            if(!StringUtils.isAlpha(lastNames[i])){
                throw new IncorrectLastNameException();
            }
            lastNames[i] = StringUtils.capitalize(lastNames[i].toLowerCase());
        }
        return String.join( "-", lastNames);
    }

}
