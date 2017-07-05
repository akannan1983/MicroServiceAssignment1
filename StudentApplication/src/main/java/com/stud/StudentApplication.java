package com.stud;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.data.Student;

@SpringBootApplication
public class StudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}
	
@RestController
class StudentService{
	ArrayList<Student> lst = new ArrayList<Student>();
	StudentService(){
		Student stud1 = new Student();
		Student stud2 = new Student();
		stud1.setId(1);
		stud1.setName("KANNAN");
		stud1.setAge(20);
		stud1.setSex("M");
		lst.add(stud1);
		stud2.setName("KALAM");
		stud2.setId(2);
		stud2.setAge(80);
		stud2.setSex("M");
		lst.add(stud2);
	}
	
	@RequestMapping(value = "/viewStudents", method = RequestMethod.GET)
	public ResponseEntity<List<Student>> viewStudents() {
		return new ResponseEntity<List<Student>>(lst, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addStudent", method=RequestMethod.GET)
	public ResponseEntity<ArrayList<Student>> addStudent(@RequestParam(value="id") int id,
															@RequestParam(value="name") String name,
															@RequestParam(value="age") int age,
															@RequestParam(value="sex") String sex){
		Student stud = new Student();
		stud.setId(id);
		stud.setName(name);
		stud.setAge(age);
		stud.setSex(sex);
		lst.add(stud);
		return new ResponseEntity<ArrayList<Student>>(lst, HttpStatus.OK);
	}
	@RequestMapping(value = "/deleteStudent", method=RequestMethod.GET)
	public ResponseEntity<ArrayList<Student>> deleteStudent(@RequestParam(value="id") int id){
		Iterator<Student> it = lst.iterator();
		while(it.hasNext()){
			Student stud = it.next();
			if(stud.getId() == id){
				it.remove();
			}
		}
		return new ResponseEntity<ArrayList<Student>>(lst, HttpStatus.OK);
	}
	@RequestMapping(value = "/modifyStudent", method=RequestMethod.GET)
	public ResponseEntity<ArrayList<Student>> modifyStudent(@RequestParam(value="id") int id,
															@RequestParam(value="name") String name,
															@RequestParam(value="age") int age,
															@RequestParam(value="sex") String sex){
		Iterator<Student> it = lst.iterator();
		Student addStud = null;
		while(it.hasNext()){
			Student stud = it.next();
			if(stud.getId() == id){
				addStud = stud;
				it.remove();
			}
		}
		if(addStud != null){
			addStud = new Student();
			addStud.setId(id);
			addStud.setAge(age);
			addStud.setName(name);
			addStud.setSex(sex);
			lst.add(addStud);
		}
		return new ResponseEntity<ArrayList<Student>>(lst, HttpStatus.OK);
	}
}
}
