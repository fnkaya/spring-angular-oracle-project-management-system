export class Pageable {

  page: number = 0;
  size: number = 0;
  totalElements: number = 0;
  totalPages: number = 0;

  constructor(size: number = 10) {
    this.size = size;
  }
}
