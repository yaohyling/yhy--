package com.boot.yhy;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.yhy.entity.Role;
import com.boot.yhy.entity.User;
import com.boot.yhy.entity.personProperties;
import com.boot.yhy.repository.UserRepository;
import com.boot.yhy.service.UserService;

@Controller
/* @ComponentScan(basePackages = {"com.boot.yhy.entity" }) */
@SpringBootApplication
/*
 * (exclude = {
 * DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
 */
public class TestSpringBoot {

	@Resource
	private personProperties person;
	@Resource
	private UserService service;

	@Resource
	private UserRepository userR;

	/*
	 * @Value("${content}") private String content;
	 */
	@ResponseBody
	@RequestMapping("/home")
	public String home(String id) {
		return person.toString();
	}

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/update") public String update(Integer id) {
	 * service.updateUser(id);
	 * 
	 * long count = userR.count();
	 * 
	 * boolean exist = userR.exists(id);
	 * 
	 * if(exist) userR.delete(id);
	 * 
	 * boolean dexist = userR.exists(id); id = 23; List<User> list = new
	 * ArrayList<User>(); for (int i = 'w'; i <= 'z'; i++) { User user = new
	 * User((char) i + "" + (char) i, id, i); id++; list.add(user);
	 * System.out.println(user); } // Iterator<User> users = list.iterator();
	 * userR.delete(list);
	 * 
	 * return "xxx"+String.valueOf(count)+"是否存在:"+exist+"删除是否成功:"+ !dexist; }
	 */

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/select") public String select(Integer age) { return
	 * userR.getByAge(age).toString(); }
	 */
	/**
	 * 分页排序
	 * 
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/page")
	public String page(Integer page) {
		System.out.println(userR.getIdsnumByuser());
		Sort sort = new Sort(Direction.DESC, "id");
		PageRequest pageable = new PageRequest(page, 5, sort);
		Specification<Role> sp = new Specification<Role>() {
			@Override
			public Predicate toPredicate(Root<Role> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				In<Object> id = cb.in(root.get("user"));
				id.value(11);
				Predicate p = cb.and(id);
				return p;
			}
		};
		/*
		 * Page p = userR.findAll(sp, pageable);
		 * System.out.println(p.getNumber()); //当前页
		 * System.out.println(p.getNumberOfElements()); //当前页记录数
		 * System.out.println(p.getSize()); //pagesize
		 * System.out.println(p.getTotalElements()); //总记录数
		 * System.out.println(p.getTotalPages()); //总页数
		 * System.out.println(p.getContent().size());
		 * System.out.println(p.getContent().get(0).toString());
		 */

		System.out.println(userR.count(sp));
		return "xx";
	}

	/**
	 * 添加或修改
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/save")
	public String save() {
		return null;
//		Role r = new Role();
//		User user = new User();
//		user.setUid(11);
//		r.setName("yhy");
//		r.setUser(user);
//		Role u = userR.save(r);
		/*
		 * List<User> users = new ArrayList<User>(); for (int i = 'a'; i <= 'z';
		 * i++) { user = new User((char) i + "" + (char) i, null, i);
		 * user.setRole(rlist); users.add(user); } Iterable<User> list =
		 * userR.save(users); System.out.println(list); System.out.println(u);
		 */
//		return u.toString();
	}

	@ResponseBody
	@RequestMapping("/zx/{id}")
	public String yhy(@PathVariable("id") Integer id) {

		return "id" + id;
	}

	@ResponseBody
	@RequestMapping("pages")
	public String pages(Integer page) {
		Sort sort = new Sort(Direction.DESC, "id");
		PageRequest pageable = new PageRequest(page, 5, sort);
		Specification<Role> sp = new Specification<Role>() {
			@Override
			public Predicate toPredicate(Root<Role> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				 CriteriaQuery<Role> q = cb.createQuery(Role.class);
				 q.multiselect(root.get("id"),root.get("name"));
				 Path<BigInteger> path = root.get("id");
				Predicate p = cb.le(path, 6);
				return p;
			}
		};
		
		  Page p = userR.findAll(sp, pageable);
		  System.out.println(p.getNumber()); //当前页
		  System.out.println(p.getNumberOfElements()); //当前页记录数
		  System.out.println(p.getSize()); //pagesize
		  System.out.println(p.getTotalElements()); //总记录数
		  System.out.println(p.getTotalPages()); //总页数
		  System.out.println(p.getContent().size());
		  System.out.println(p.getContent().get(0).toString());
		return "ok";
	}

	public static void main(String[] args) {
		SpringApplication.run(TestSpringBoot.class, args);
	}
}
