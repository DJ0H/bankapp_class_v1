package com.tenco.bankapp.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.bankapp.dto.HistoryResponseDto;
import com.tenco.bankapp.repository.entity.History;

@Mapper
public interface HistoryRepository {
	public int insert(History history);
	public int updateById(History history);
	public int deleteById(int id);
	public List<History> findAll(); 
	public History findById();
	// 코드 추가 
	// 파라미터가 하나 이상인 경우 @param 사용해서 명시 하자.  
	public List<History> findByIdAndDynamicType(@Param("accountId") Integer id, @Param("type") String type);
}
