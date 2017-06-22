package by.artemyeu.betting.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Created by Acer on 16.05.2017.
 */
public class DeletedTag extends TagSupport {

    /** The is deleted. */
    private final String IS_DELETED = "is_deleted";

    /**
     * Do start tag.
     *
     * @return the int
     * @throws JspException the jsp exception
     */
    @Override
    public int doStartTag() throws JspException {
        Object isDeleted = pageContext.getSession().getAttribute(IS_DELETED);
        if (isDeleted!=null && Boolean.valueOf(isDeleted.toString())) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }
}

