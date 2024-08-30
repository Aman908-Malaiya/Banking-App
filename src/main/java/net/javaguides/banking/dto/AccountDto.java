package net.javaguides.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data   //to automatically generate constructor getter setter methods
/**
 annotation is used to generate a constructor that takes all the fields of a class as arguments.
 This eliminates the need to manually write a constructor that initializes all the fields.
 **/
@AllArgsConstructor
public class AccountDto {

    private Long id;
    private String accountHolderName;
    private double balance;
}
