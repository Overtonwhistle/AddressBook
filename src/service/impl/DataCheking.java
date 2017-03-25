package service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.Record;

public class DataCheking {

	public static String recordDataCheck(Record record) {
		String errors = "";
		if (record.getName().trim().length() == 0) {
			errors = errors + " Input data errors:\n Empty Name field  ";
		}

		String number = record.getPhoneNumber();
		if ((number.length() != 13) || (!number.substring(1).matches("[-+]?\\d+"))) {
			errors = errors + "\n Phone Number is not correct";
		}

		String email = record.getEmail();

		if (!(emailValidate(email) || email == "_empty_")) {
			errors = errors + "\n e-mail is not correct";
		}

		return errors;
	}

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public static boolean emailValidate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

}
