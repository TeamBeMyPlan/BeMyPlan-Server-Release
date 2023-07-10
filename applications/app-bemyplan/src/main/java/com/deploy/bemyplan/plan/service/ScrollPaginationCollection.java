package com.deploy.bemyplan.plan.service;

import java.util.List;

/**
 * 스크롤 페이지네이션을 위한 컬렉션
 */
class ScrollPaginationCollection<T> {

    private final List<T> itemsWithNextCursor; // 현재 스크롤의 요소 + 다음 스크롤의 요소 1개 (다음 스크롤이 있는지 확인을 위한)
    private final int countPerScroll;

    private ScrollPaginationCollection(List<T> itemsWithNextCursor, int countPerScroll) {
        this.itemsWithNextCursor = itemsWithNextCursor;
        this.countPerScroll = countPerScroll;
    }

    public static <T> ScrollPaginationCollection<T> of(List<T> itemsWithNextCursor, int size) {
        return new ScrollPaginationCollection<>(itemsWithNextCursor, size);
    }

    public boolean isLastScroll() {
        return this.itemsWithNextCursor.size() <= countPerScroll;
    }

    public List<T> getCurrentScrollItems() {
        if (isLastScroll()) {
            return this.itemsWithNextCursor;
        }
        return this.itemsWithNextCursor.subList(0, countPerScroll);
    }

    public T getNextCursor() {
        return itemsWithNextCursor.get(countPerScroll - 1);
    }
}
