OPTIONS=pdf html chunk clean
OBJS=\
	main.xml\
	signature.xml

all:
	@echo "Opciones: $(OPTIONS)"

pdf: main.pdf params_pdf.xsl

main.pdf: $(OBJS) main.fo params_pdf.xsl
	fop -fo main.fo -pdf main.pdf

main.fo: $(OBJS) params_pdf.xsl
	xmlto -m params_pdf.xsl fo main.xml

html: main.html $(OBJS) params_html.xsl

main.html: $(OBJS) params_html.xsl
	xmlto -m params_html.xsl html-nochunks main.xml

chunk: html/index.html $(OBJS) params_html.xsl

html/index.html: $(OBJS) params_html.xsl
	[ -d html ] || mkdir html
	(cd html; xmlto -m ../params_html.xsl html ../main.xml)
	#cp -r figures html

clean:
	-rm -rf html main.html main.fo main.pdf

.PHONY: $(OPTIONS)
