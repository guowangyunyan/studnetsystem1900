package com.training.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.training.common.service.CommonService;
import com.training.convert.StudentDataConvert;
import com.training.convert.StudentModelConvert;
import com.training.dao.StudentDao;
import com.training.data.StudentData;
import com.training.form.StudentForm;
import com.training.model.StudentModel;
import com.training.page.Pagination;
import com.training.page.SearchResult;
import com.training.service.StudentService;

public class StudentServiceImpl implements StudentService {
	private StudentModelConvert studentModelConvert;
	private CommonService commonService;
	private StudentDataConvert studentDataConvert;
	private StudentDao studentDao;

	// 添加学生信息
	@Override
	public void addStudent(StudentForm studentForm) {
		StudentModel model = studentModelConvert.convert(studentForm);
		commonService.saveOrUpdateEntity(model);
	}

	// 根据条件分页查询学生
	@Override
	public SearchResult<StudentData> loadStudentsByFields(StudentForm studentForm, Pagination page) {
		Map<String, Object> params = new HashMap<>();
		if (StringUtils.isNotBlank(studentForm.getName())) {
			params.put(StudentModel.NAME, studentForm.getName());
		}
		if (StringUtils.isNotBlank(studentForm.getClazz())) {
			params.put(StudentModel.CLAZZ, studentForm.getClazz());
		}
		SearchResult<StudentModel> searchResults = studentDao.queryStudentByFields(params, page);
		SearchResult<StudentData> results = new SearchResult<StudentData>();
		List<StudentData> datas = new ArrayList<>();
		for (StudentModel model : searchResults.getResult()) {
			datas.add(studentDataConvert.convert(model));
		}
		results.setPagination(searchResults.getPagination());
		results.setResult(datas);
		return results;
	}

	@Override
	public StudentData queryStudnetById(StudentForm studentForm) {
		StudentModel studentModel = (StudentModel) commonService.load(StudentModel.class, studentForm.getId());
		return studentDataConvert.convert(studentModel);
	}

	@Override
	public void updateStudent(StudentForm studentForm) {
		StudentModel studentModel = (StudentModel) commonService.load(StudentModel.class, studentForm.getId());
		studentModel.setModifyTime(new Date());
		studentModel.setClazz(studentForm.getClazz());
		studentModel.setName(studentForm.getName());
		commonService.saveOrUpdateEntity(studentModel);

	}

	@Override
	public void deleteStudent(StudentForm studentForm) {
		StudentModel model = new StudentModel();
		model.setId(studentForm.getId());
		commonService.delete(model);
	}

	public StudentModelConvert getStudentModelConvert() {
		return studentModelConvert;
	}

	public void setStudentModelConvert(StudentModelConvert studentModelConvert) {
		this.studentModelConvert = studentModelConvert;
	}

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public StudentDataConvert getStudentDataConvert() {
		return studentDataConvert;
	}

	public void setStudentDataConvert(StudentDataConvert studentDataConvert) {
		this.studentDataConvert = studentDataConvert;
	}

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

}
