package hackathon.nttdata.coderpath.alumnowebflux.redisson.test.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	private String name;
	private int age;
	private String city;
	private List<Integer> marks;
	
}
