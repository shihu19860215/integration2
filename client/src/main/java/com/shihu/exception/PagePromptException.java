package com.shihu.exception;

import com.shihu.entity.PagePrompt;

public class PagePromptException extends RuntimeException {
    private PagePrompt pagePrompt;

    public PagePromptException(Integer id) {
        this.pagePrompt = PagePrompt.getPagePrompt(id);
    }

    public PagePrompt getPagePrompt() {
        return pagePrompt;
    }

    public PagePromptException setPagePrompt(PagePrompt pagePrompt) {
        this.pagePrompt = pagePrompt;
        return this;
    }
}
