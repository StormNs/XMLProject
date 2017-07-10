<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
                <div class="lastest-row">
                    <xsl:for-each select="movies/movie">
                        <div class="movie-container">
                            <xsl:element name="a">
                                <xsl:attribute name="href">
                                    <xsl:value-of select="id"/>
                                </xsl:attribute>
                                <xsl:element name="img">
                                    <xsl:attribute name="class">movie-picture</xsl:attribute>
                                    <xsl:attribute name="alt">
                                        <xsl:value-of select="alternateName"/>
                                    </xsl:attribute>
                                    <xsl:attribute name="src">
                                        
                                            FileServlet/<xsl:value-of select="imageCover" />
                                        
                                        
                                    </xsl:attribute>
                                </xsl:element>
                                <xsl:element name="div">
                                    <xsl:attribute name="class">movie-title-overlay</xsl:attribute>
                                    <xsl:value-of select="name"/>
                                </xsl:element>
                            </xsl:element>
                        </div>
                    </xsl:for-each>
                </div>
    </xsl:template>
   
</xsl:stylesheet>
