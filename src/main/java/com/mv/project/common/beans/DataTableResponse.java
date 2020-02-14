package com.mv.project.common.beans;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DataTableResponse<T> {
	private Integer draw = 0;
	private Integer recordsTotal = 0;
	private List<T> data;
}
