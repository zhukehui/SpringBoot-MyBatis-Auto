# springboot-mybatis-generator

#### 介绍

springboot+mybatis-plus-generator 自定义模板产生基础controller,service,dao,entity,XML,已有CRUD接口

#### 生成内容

# Controller

```
/**
 * <p>
 * 学生信息表 前端控制器
 * </p>
 *
 * @author fhd
 * @since 2020-09-30
 */
@RestController
@RequestMapping("/st/student")
public class StudentController {
	
	private final Logger LOG = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentService studentService;

	/**
	 * 	添加
	 */
	@PostMapping("/saveStudent")
	public RestResult saveStudent(@RequestBody Student student) {
		studentService.saveStudent(student);
		return RestResult.success();
	}
	
	/**
	 * 	id查询
	 */
	@PostMapping("/findStudentById")
	public RestResult findStudentById(@RequestBody Student student) {
		Student ojb = studentService.findStudentById(student);
		return RestResult.success(ojb);
	}
	
	/**
	 * 	分页查询
	 */
	@PostMapping("/findStudentList")
	public RestResult findStudentList(@RequestBody Student student) {
		List<Student> list = studentService.findStudentList(student);
		return RestResult.success(list);
	}
	
	/**
	 * 	修改
	 */
	@PostMapping("/updateStudent")
	public RestResult updateStudent(@RequestBody Student student) {
		studentService.updateStudent(student);
		return RestResult.success();
	}
	
	/**
	 * 	删除
	 */
	@PostMapping("/deleteStudent")
	public RestResult deleteStudent(@RequestBody Student student) {
		studentService.deleteStudent(student);
		return RestResult.success();
	}

}
```

# Service

```
/**
 * <p>
 * 学生信息表 服务实现类
 * </p>
 *
 * @author fhd
 * @since 2020-09-30
 */
@Service
public class StudentServiceImpl implements StudentService {

	private final Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Autowired
	private StudentMapper studentMapper;

	/**
	 * 	添加
	 */
	@Override
	public void saveStudent(Student student) {
		studentMapper.saveStudent(student);
	}
	
	/**
	 * 	id查询
	 */
	@Override
	public Student findStudentById(Student student) {
		Student ojb = studentMapper.findStudentById(student);
		return ojb;
	}
	
	/**
	 * 	分页查询
	 */
	@Override
	public List<Student> findStudentList(Student student) {
		List<Student> list = studentMapper.findStudentList(student);
		return list;
	}
	
	/**
	 * 	修改
	 */
	@Override
	public void updateStudent(Student student) {
		studentMapper.updateStudent(student);
	}
	
	/**
	 * 	删除
	 */
	@Override
	public void deleteStudent(Student student) {
		studentMapper.deleteStudent(student);
	}

}
```

# XML

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.fhd.springboot.st.mapper.StudentMapper">

    <!-- 开启二级缓存 -->
    <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>

    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="com.fhd.springboot.st.entity.Student">
        <id column="id" property="id" />
        <result column="name" property="name" />
        <result column="age" property="age" />
        <result column="sex" property="sex" />
    </resultMap>

    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
    	id, name, age, sex
    </sql>
	
	<!-- 通用查询语句(数据库comment中含有?则不显示字段注释)) -->
	<sql id="Base_Where_List">
		<!-- 学生id查询 -->
	    <if test="id != null and id != ''">
	        AND id = #{id}
	    </if>
    		<!-- 姓名查询 -->
	    <if test="name != null and name != ''">
	        AND name = #{name}
	    </if>
    	<!-- 姓名模糊查询 -->
	    <if test="name != null and name != ''">
	        AND name LIKE CONCAT('%', #{name}, '%')
	    </if>
		<!-- 年龄查询 -->
	    <if test="age != null and age != ''">
	        AND age = #{age}
	    </if>
    		<!-- 性别 0女 1男查询 -->
	    <if test="sex != null and sex != ''">
	        AND sex = #{sex}
	    </if>
    </sql>
	
   <!-- 添加 -->
   <insert id="insert" parameterType="java.util.Map" >
        INSERT INTO student (
	    	id, name, age, sex
        )
        VALUES(
        	<if test="id != null">
			#{id},
        	</if>
        	<if test="name != null">
			#{name},
        	</if>
        	<if test="age != null">
			#{age},
        	</if>
        	<if test="sex != null">
			#{sex},
        	</if>
        )
    </insert>
	
	<!-- 更新 -->
    <update id="updateStudent" parameterType="java.util.Map" >
        UPDATE student
        <set>
        	<if test="name != null">
				name = #{name},
        	</if>
        	<if test="age != null">
				age = #{age},
        	</if>
        	<if test="sex != null">
				sex = #{sex},
        	</if>
		</set>
        WHERE id = #{id}
    </update>
	
	<!-- id查询 -->
	<select id="findStudentById" parameterType="java.util.Map" resultMap="BaseResultMap">
        SELECT 
        <include refid="Base_Column_List" />
        FROM student
        WHERE id = #{id}
    </select>
    
    <!-- 分页查询-->
    <select id="findStudentList" parameterType="java.util.Map" resultMap="BaseResultMap">
        SELECT 
        <include refid="Base_Column_List" />
        FROM student
        <where>
        	<if test="id != null">
				and id = #{id}
        	</if>
        	<if test="name != null">
				and name = #{name}
        	</if>
        	<if test="age != null">
				and age = #{age}
        	</if>
        	<if test="sex != null">
				and sex = #{sex}
        	</if>
		</where>
        LIMIT ${(pageNo-1)*pageSize},${pageSize}
    </select>
    
	<!-- 删除-->
    <delete id="deleteStudent" parameterType="java.util.Map" >
        DELETE FROM student
        WHERE id = #{id}
    </delete>
</mapper>

```

