package wang.auspicous.ausp1ciouslib.net;

import okhttp3.MediaType;

public interface MediaTypes {
    public MediaType APPLICATION_ATOM_XML_TYPE = MediaType.parse("application/atom+xml;charset=utf-8");
    public MediaType APPLICATION_FORM_URLENCODED_TYPE = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
    public MediaType APPLICATION_JSON_TYPE = MediaType.parse("application/json;charset=utf-8");
    public MediaType APPLICATION_OCTET_STREAM_TYPE = MediaType.parse("application/octet-stream");
    public MediaType APPLICATION_XHTML_XML_TYPE = MediaType.parse("application/xhtml+xml;charset=utf-8");
    public MediaType APPLICATION_XML_TYPE = MediaType.parse("application/xml;charset=utf-8");
    public MediaType MULTIPART_FORM_DATA_TYPE = MediaType.parse("multipart/form-data;charset=utf-8");
    public MediaType TEXT_HTML_TYPE = MediaType.parse("text/html;charset=utf-8");
    public MediaType TEXT_XML_TYPE = MediaType.parse("text/xml;charset=utf-8");
    public MediaType TEXT_PLAIN_TYPE = MediaType.parse("text/plain;charset=utf-8");
    public MediaType IMAGE_TYPE = MediaType.parse("image/*");
    public MediaType WILDCARD_TYPE = MediaType.parse("*/*");
}
