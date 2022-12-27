package uk.co.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntToRomanConverterTest {

    @Test
    void test3000() {
        IntToRomanConverter testConvertor = new IntToRomanConverter();
        String result = testConvertor.convert(3000);
        assertEquals("MMM",result);
    }
    @Test
    void test2900() {
        IntToRomanConverter testConvertor = new IntToRomanConverter();
        String result = testConvertor.convert(2900);
        assertEquals("MMCM",result);
    }
    @Test
    void test2905() {
        IntToRomanConverter testConvertor = new IntToRomanConverter();
        String result = testConvertor.convert(2905);
        assertEquals("MMCMV",result);
    }
    @Test
    void test2904() {
        IntToRomanConverter testConvertor = new IntToRomanConverter();
        String result = testConvertor.convert(2904);
        assertEquals("MMCMIV",result);
    }
    @Test
    void test2994() {
        IntToRomanConverter testConvertor = new IntToRomanConverter();
        String result = testConvertor.convert(2994);
        assertEquals("MMCMXCIV",result);
    }


    @Test
    void nothing(){
        assertEquals(true,true);
    }
}