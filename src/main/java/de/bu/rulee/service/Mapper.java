package de.bu.rulee.service;

public interface Mapper<I, O> {

	O map(I input);

	I mapReverse(I output);

}
