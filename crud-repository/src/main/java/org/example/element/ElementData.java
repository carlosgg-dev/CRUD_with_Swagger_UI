package org.example.element;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ElementData {

	int id;
	String name;
	String type;
}
