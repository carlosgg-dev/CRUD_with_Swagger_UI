package org.example.element;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ELEMENTS")
@RequiredArgsConstructor
@AllArgsConstructor
public class ElementData {

	@Id
	int id;

	@Column(name = "name")
	String name;
}
