package com.pack.Employee360.UserRegistration.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LeaveDetails {
	@Id
	private Integer empId;
	private String empName;
	private String leaveType;
//	@DateTimeFormat(pattern = "yyyy/mm/dd")
	private String startDate;
//	@DateTimeFormat(pattern = "yyyy/mm/dd")
	private String endDate;
//	@DateTimeFormat(pattern = "yyyy/mm/dd")
	private String appliedOn;
	private Integer noOfDays;
	private String reason;
	private String status;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

}
