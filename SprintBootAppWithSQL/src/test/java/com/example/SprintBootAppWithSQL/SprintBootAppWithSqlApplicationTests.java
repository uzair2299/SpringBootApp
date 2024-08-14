package com.example.SprintBootAppWithSQL;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
class SprintBootAppWithSqlApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void parsing() {
        //	String input = "58887107#NewInboundCall#<from_address>#<callvariable1,callvariable2,..>#DialogID:<dialog_ID>#CallType:<call_type>#<start_time>";


        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // The stored BCrypt hash
        String storedHash = "$2a$10$DowrX0AfF/nDZ8D0czB..uz/6vplU9l1QbZBD3MLF2/yyROFpAGTC";

        // The plaintext password to verify
        String rawPassword = "asdfghA1"; // Replace this with the actual password to verify
        String encode = passwordEncoder.encode(rawPassword);
        // Verifying the password against the stored hash
        boolean isPasswordMatch = passwordEncoder.matches(rawPassword, encode);

        if (isPasswordMatch) {
            System.out.println("Password matches!");
        } else {
            System.out.println("Password does not match.");
        }


        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> squaredEvenNumbers = numbers.stream()
                .map(n -> {
                    log.info("Mapping: " + n + " by " + Thread.currentThread().getName());
                    return n * n;
                })
                .filter(n -> {
                    log.info("Filtering: " + n + " by " + Thread.currentThread().getName());
                    return n % 2 == 0;
                })
                .collect(Collectors.toList());

        System.out.println("Parallel Result: " + squaredEvenNumbers);


//		String input = "5000#InboundCall#ACTIVE#DialogID:17672721#42512#ani=42512|associatedDialogId=17672718|callVariable1=|callVariable2=|callVariable3=null|callVariable4=CONSULT|callVariable5=53da5c96-6580-47d9-958f-28772d8bf45d|callVariable6=|callVariable7=42035|callVariable8=MAINAPP|callVariable9=|callVariable10=|user.microapp.locale=en-us|user.microapp.media_server=http://192.168.1.72|user.microapp.fetchdelay=D|user.microapp.app_media_lib=app|user.cvp_server_info=192.168.1.72#2022-07-22 18:16:47#CallType:CONSULT_IN#ParticipantsCount:2#participants:42053,ACTIVE,AGENT_DEVICE,,2022-07-22 18:16:47|42512,ACTIVE,AGENT_DEVICE,,2022-07-22 18:16:47 NMSCorrelationID: 121f46a3-05e9-4e29-96f3-23490b72ce4a Sent Time 22-Jul-22 6:16:23 PM LastGCUpdate: 22-Jul-22 6:16:46 PM Build <> 4.2.9  07-22-2022 12:10PM";
//		String callVariable = "ani=42512|user.ANI=5578874|associatedDialogId=17672718|callVariable1=|callVariable2=|callVariable3=null|callVariable4=CONSULT|callVariable5=53da5c96-6580-47d9-958f-28772d8bf45d|callVariable6=|callVariable7=42035|callVariable8=MAINAPP|callVariable9=|callVariable10=|user.microapp.locale=en-us|user.microapp.media_server=http://192.168.1.72|user.microapp.fetchdelay=D|user.microapp.app_media_lib=app|user.cvp_server_info=192.168.1.72";
//		// Split the string by '#'
//		String[] parts = input.split("#",4);
//
//		String[] callVariableArray = callVariable.split("\\|");
//
//		String[] user_ani = callVariable.split("user.ANI=");
//
//
//		// Print callVariableArray part of the array
//		for (String part : callVariableArray) {
//			System.out.println(part);
//		}
//
//		// Print each part of the array
//		for (String part : parts) {
//			System.out.println(part);
//		}
    }

}
